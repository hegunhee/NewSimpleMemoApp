# New 덜 편한 가계부
## 소개  
기존에 했었던 덜 편한 가계부에서 데이터바인딩, Unit Test등의 새로운 기술들을 사용해  
다시 만들어 봤습니다.    
## App 화면  

## 프로젝트 구조  
MVVM + DataBinding  
## 사용된 라이브러리  
* Architecture
  - ViewModel  
  - LiveData  
* UI
  - ConstraintLayout  
  - RecyclerView  
  - Fragment  
* Third Party  
  - Kotlin Coroutine
  - Koin  
  - Room  
## 진척도  
1) 가계부에 관한 Test Code 작성  
2) Test Code에 Koin 도입 및 작동하는지 확인  
3) Room에서 데이터를 불러올때 연도별, 월별로 정렬된 데이터 호출  
5) BottomNavigationView 도입  
----------------완료-------------------  
7) UI 작성(mainActivity -> addMemoActivity -> 메모 확인 View -> 통계)  
8) ViewModel 및 Repository 코드 작성  
9) DataBinding 도입
10) Adpater 및 BindingAdapter 작성  
## 이후 개선될 사항  
현재 계획으로는 데이터 추가, 확인, 지출, 수입별 통계까지이지만 더 추가 예정  
