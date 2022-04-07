# New 덜 편한 가계부
## 소개  
"편한 가계부" 앱을 모티브로해서 만들었습니다.  
가계부를 추가, 수정, 삭제를 할 수 있습니다.  
통계 탭을통해 이번달에 어떤 항목(식비,교통,기타)으로 얼마를 사용했는지 볼 수 있습니다.  
## 앱 사진  
![memoView](https://user-images.githubusercontent.com/57277631/160281890-b0bc213d-128e-441f-80d8-1c6c37e12245.PNG)  
시작할때, 가계부 화면  
![addMemoView](https://user-images.githubusercontent.com/57277631/160281895-6e1661ae-5b0c-4e09-a58e-09a6b11fcf09.PNG)  
가계부 추가, FloatingActionButton 클릭시 이동  
![detailMemoView](https://user-images.githubusercontent.com/57277631/160281897-da526653-76e4-4a56-8530-3d3ff4c39f61.PNG)  
가계부 수정 및 삭제, 클릭시 이동  
![staticsMemoView](https://user-images.githubusercontent.com/57277631/161213715-31ee22dc-26b3-44a5-b11e-a8909fec2053.PNG)  
통계 탭  

## 사용된 라이브러리  
* JetPack
  - AAC-ViewModel  
  - LiveData  
* UI
  - ConstraintLayout  
  - RecyclerView  
  - Fragment  
* Third Party  
  - Kotlin Coroutine
  - Koin  
  - Room  
## 기술 정보  
  * **AAC-ViewModel** 적용  
    Activity나 Fragment 코드가 비대해지는것을 막고 데이터를 관리하는 데 사용합니다.  
    액티비티가 프래그먼트가 화면 회전 등의 이유로 Destroy 상태가 되어도 AAC-ViewModel은 Destroy 되지않기때문에  
    데이터를 쉽게 관리할 수 있습니다. 그리고 ViewModelScope를 사용해 Coroutine을 쉽게 사용할 수 있습니다.  
  * **LiveData** 적용  
    LiveData를 사용해서 View에서 ViewModel의 LiveData를 옵저빙해 Adapter에 데이터를 쉽게 set할 수 있게 하였습니다.  
    DataBinding으로 xml과 코드를 결합시킬때 LiveData를 사용해 데이터를 쉽게 연동하였습니다.  
  * **DataBinding** 적용  
    dataBinding을 사용하여 findViewById를 사용하지 않으며 xml 파일과 데이터를 연결해줍니다.  
    그 이외에도 데이터바인딩 변수를 viewModel로 만들어 viewModel에서 만든 함수를 Button의 onClick메소드로 사용할 수 있습니다.  
    Listener 에서 Context를 사용해야하는경우는 ViewModel에 Context를 할당하면 안되기때문에  
    어쩔 수 없이 Activity나 Fragment에서 구현하였습니다.  
  * **Room DataBase** 적용  
    가계부라는 특성상 가계부 데이터를 저장하고 불러오는 과정이 필요한데  
    Room을 이용해서 데이터베이스 생성, 테이블 관리, DAO(Data Access Object)를 쉽게 관리하였습니다.
  * **Coroutine** 적용  
    Room을 사용하거나 가계부 정보들을 정렬하는데 많은 시간이 필요하기때문에  
    Coroutine을 사용해서 main Thread의 부담을 줄일 수 있음
  * **Koin** 적용  
    Activity에서 ViewModel을 사용할때 의존성이나 Model의 Repository를 사용할때  
    의존성 주입을 위해 Koin을 사용하였습니다.  
    러닝커브가 낮고 사용하기 쉬웠습니다.  
## 진척도  
1) 가계부에 관한 Test Code 작성  
2) Test Code에 Koin 도입 및 작동하는지 확인  
3) Room에서 데이터를 불러올때 연도별, 월별로 정렬된 데이터 호출  
4) BottomNavigationView 도입  
5) MemoViewModel 및 Repository 코드 작성 및 MemoFragment UI 작성  
6) MemoViewModel DataBinding 도입  
7) Adapter 및 LiveData Observe 사용  
8) DetailMemoActivity작성  
9) DetailMemoActivity로직까지 완성  
10) 통계Fragment xml 작성  
11) 통계Fragment 로직 작성  
12) Detail통계 Activity xml 및 ViewModel 작성  
----------------완료-------------------  
13) Adapter작성 및 DB코드 작성
14) 만들어놓은 사항 코드 개선  
## 이후 개선될 사항  
현재 계획으로는 데이터 추가, 확인, 지출, 수입별 통계까지이지만 더 추가 예정  
현재 Fragment가 초기화되지않아 추가, 수정시 Adapter에 드러나지않음 향후 방법을 모색해야됨 -> Fragment의 onResume 생명주기를 활용(완)  
통계 탭에서 데이터의 price 값이 Int의 범위 혹은 Double의 범위에 넘어갈경우 방법을 모색해야됨  
## 위기 및 극복  
1) **RecyclerView Adapter** 데이터 변경 **(MemoFragment, StaticsFragment)**  
https://github.com/hegunhee/NewSimpleMemoApp/issues/2  
2) **Fragment 생명주기**를 이용한 데이터 재호출 **(MemoFragment)**  
https://github.com/hegunhee/NewSimpleMemoApp/issues/3  
3) **BaseFragment 사용**  
https://github.com/hegunhee/NewSimpleMemoApp/issues/4  
