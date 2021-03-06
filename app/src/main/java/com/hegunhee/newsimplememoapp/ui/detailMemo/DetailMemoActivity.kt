package com.hegunhee.newsimplememoapp.ui.detailMemo

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.hegunhee.newsimplememoapp.R
import com.hegunhee.newsimplememoapp.data.entity.Memo
import com.hegunhee.newsimplememoapp.data.entity.assetArray
import com.hegunhee.newsimplememoapp.data.entity.expenseAttr
import com.hegunhee.newsimplememoapp.data.entity.incomeAttr
import com.hegunhee.newsimplememoapp.databinding.ActivityDetailMemoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class DetailMemoActivity : AppCompatActivity() {

    private val viewModel : DetailMemoViewModel by viewModels()
    private lateinit var binding: ActivityDetailMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_memo)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@DetailMemoActivity
        }
        intent?.getParcelableExtra<Memo>("Memo")?.let {
            viewModel.initViewModel(it)
        } ?: kotlin.run {
            viewModel.initViewModel(navArgs<DetailMemoActivityArgs>().value.detailMemo)
        }

        observeData()
    }

    private fun observeData() = viewModel.memoState.observe(this) {
        when (it) {
            DetailMemoState.Uninitialized -> {}
            DetailMemoState.Back -> {
                onBackPressed()
            }
            DetailMemoState.Save -> {
                saveData()
            }
            DetailMemoState.SetAsset -> {
                setAsset()
            }
            DetailMemoState.SetAttr -> {
                setAttr()
            }
            DetailMemoState.SetDate -> {
                setDate()
            }
            DetailMemoState.SetTime -> {
                setTime()
            }
            DetailMemoState.Remove -> {
                finish()
            }
        }
    }

    fun saveData() {
        with(viewModel) {
            if (asset.value.isNullOrEmpty()) {
                setAsset()
            } else if (attr.value.isNullOrEmpty()) {
                setAttr()
            } else if (price.value.isNullOrEmpty()) {
                Toast.makeText(
                    this@DetailMemoActivity,
                    "????????? ??????????????????",
                    android.widget.Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                saveData()
                finish()
            }
        }
    }

    private fun setAsset() {
        AlertDialog.Builder(this)
            .setTitle("??????")
            .setItems(
                assetArray,
                DialogInterface.OnClickListener { dialogInterface, which ->
                    viewModel.asset.value = assetArray[which]
                }).create().show()
    }

    private fun setAttr() {
        val attrType = if (viewModel.category.value == "??????") {
            incomeAttr
        } else {
            expenseAttr
        }
        AlertDialog.Builder(this)
            .setTitle("??????")
            .setItems(attrType,
                DialogInterface.OnClickListener { dialogInterface, which ->
                    viewModel.attr.value = attrType[which]
                }).create().show()
    }

    private fun setDate() {
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            viewModel.setDate(LocalDate.of(year, month + 1, dayOfMonth))
        }.let { listener ->
            with(viewModel) {
                DatePickerDialog(this@DetailMemoActivity, listener, year, month - 1, day).show()
            }
        }

    }

    private fun setTime() {
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            with(viewModel) {
                if (hourOfDay > 12) {
                    ampm = "??????"
                    hour = hourOfDay - 12
                    this.minute = minute
                } else {
                    ampm = "??????"
                    hour = hourOfDay
                    this.minute = minute
                }
                Toast.makeText(
                    this@DetailMemoActivity,
                    "${ampm},${hour}:${minute}",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
                setTimeInfo()
            }

        }?.let { listener ->
            TimePickerDialog(this, listener, viewModel.hour, viewModel.minute, false).show()
        }
    }

}