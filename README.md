# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


## STEP1 문자열 계산기 - 구현 기능
* 입력한 문자열 값에 따라 사칙연산을 수행할 수 있다.
    * 덧셈
    * 뺄셈
    * 나눗셈 - 결과는 정수로 한정
    * 곱셈
* 문자열의 사칙 연산 사이에는 빈 공백 문자열이 있다.
* 사칙연산 우선 순위가 아닌 입력 값에 따른 계산 순서 결정
* 예외처리 

## STEP2 로또(자동) - 구현 기능
* 구입금액을 입력 받는다.
* 1000원당 1회시도 횟수가 주어진다.
* 랜덤 숫자 6개를 생성한다.
* 당첨 번호를 입력받는다.
* 당첨 번호의 일치 개수를 구한다.
* 수익율을 계산한다.
