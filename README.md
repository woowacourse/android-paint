# android-paint

## 1단계 - 자유 곡선

- [x] 화면에 자유 곡선을 그릴 수 있다.
- [x] 도구 패널을 추가한다.
    - [x] 브러시 색상을 변경할 수 있다. (브러시 색상은 빨, 주, 노, 초, 파로 한정)
    - [x] 브러시 굵기를 변경할 수 있다.
  
## 2단계 - 도형

### 기능 요구 사항

그림판 앱에 기능을 추가한다.

- [x] 화면에 사각형, 원 모양을 그릴 수 있도록 구현한다.
    - [x] 사용자가 자유롭게 크기나 위치를 조정할 수 있다.
- [x] 특정 영역의 요소를 지울 수 있는 지우개를 구현한다.
- [x] 브러쉬(펜, 사각형, 원, 지우개) 종류를 자유롭게 선택해서 그릴 수 있다.

### 선택 요구 사항

- [x] 전체 그림을 지우는 기능을 추가한다.
- [ ] 취소(undo), 재실행(redo)을 구현한다.

## 3단계 - 반응형 UI

### 기능 요구 사항

그림판 앱을 반응형으로 구현한다.

- [x] 태블릿 디바이스의 세로(portrait)/가로(landscape)화면에서 어색해보이지 않도록 구현한다.
    - [x] 구현 예시를 참고하여 태블릿 세로 화면에서는 도구 패널의 폭을 줄인다.
    - [x] 구현 예시를 참고하여 태블릿 가로 화면에서는 도구 패널을 왼쪽 상단으로 옮긴다.
    - [x] 만약 본인이 작성한 UI가 구현 예시를 참고하기 어려운 상태라면 융통성있게 구현한다.
- [x] 다크모드를 대응한다.
    - [x] 구현 예시를 참고하여 다크모드 전환 시 그림판 보드의 색상과 상태바 색상이 검은색으로 바뀌어야 한다.
    - [x] 이외의 도구 패널 색상 등은 융통성있게 구현한다.