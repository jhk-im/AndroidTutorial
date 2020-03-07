# AndroidTutorial
안드로이드 개발을 하면서 새로 접하거나 공부하게 되는 내용들을 정리해 두었다. 

# Android Basic Tutorials
기본적인 튜토리얼을 모아둔 디렉토리이다. 

// 프로젝트 
- Debug Tutorial
- Handler Tutorial
- Sample Android
- Sample Maps API
- Sample News App 

# Android Test
테스트에 관련된 내용들을 모아둔 디렉토리이다. 

//앱 개발시 테스트를 해야하는 이유
1) 자신의 코드에 대한 안전성 검사
2) 코드 리팩토링시 기존에 돌아가던 로직을 검사

//테스트의 종류 
1) Unit Test
- 일반적으로 코드의 유닛 단위의 기능을 실행하는 방식 
- JUnit, Mockito, PowerMock
*유닛단위 -> 메소드, 클래스, 컴포넌트 등 

2) UI Test
- 사용자 인터랙션을 평가 
- Espresso, UIAutomator, Robotiup, Robolectric 
*인터랙션 -> 버튼클릭, 텍스트 입력 등

// 프로젝트
- UnitTest&UiTest

# Proguard 

// Proguard?
코드 난독화 및 안드로이드 앱의 용량을 줄여주는 프로그램 

// 프로가드가 없다면?
- 프로젝트 코드가 난독화가 되지 않아 디버그 시 코드 노출 우려
- 불필요한 코드가 앱 컴파일시 포함 되어 불필요하게 앱의 용량(비용) 증가
- 불필요한 코드가 컴파일시 포함되기 때문에 메서드 65536개 초가롸 멀티덱스를 사용하게 됨 
*멀티덱스 - 안드로이드 앱을 구성한느 코드는 컴파일되어 덱스 파일로 만들어진다. 하나의 덱스 파일에는 최대 65536개 메소드만 참조할 수 있다. 만약 65536개의 메소드가 초과하게 되면 덱스 파일이 여러개 생성되고 이를 멀티 덱스라 한다. 

// 프로가드의 장점
- 프로젝트 코드 난독화 (디컴파일시 난독화 되어 코드읽기 어려움)
- 프로젝트에서 사용하지 않는 메서드 제거 
- 불필요한 메서드 제거로 인해 멀티덱스를 사용하지 않을 수 있음 

// 단점
- 난독화가 진행 되어 프로젝트 이름, 라인 넘버가 제거되어 디버그가 어려움
- 라이브러리 추가 후 난독화 시 warning 발생 

// 프로젝트 
- AndroidProguardSample

# Library
자주 사용하는 라이브러리에 관련된 내용들을 모아둔 디렉토리

// 프로젝트
- Room Tutorial 
- 

# UI
UI에 관련된 내용들 정리해 둔 디렉토리 


# TEST
https://developerjj.blogspot.com/ 

안드로이드 
- 4대컴포넌트 예제
