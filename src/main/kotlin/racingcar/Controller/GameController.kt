package racingcar.Controller

class GameController {
    fun startGame() {

        val carNameInput = carName()

        for (car in carNameInput) {
            if (car.length > 5) {
                throw IllegalArgumentException("자동차 이름은 5자 이하만 가능합니다.")
            }
        }

        val tryCountInput = tryCount()
        if (tryCountInput <= 0) {
            throw IllegalArgumentException("시도 횟수는 0보다 커야 합니다.")
        }

        val carMove = mutableMapOf<String, Int>()
        for (i in 1..tryCountInput) {
            println()
            moveCar(carNameInput, carMove)
        }

        val winners = winnersList(carMove)
        printWinners(winners)

    }
    private fun carName(): List<String> {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        val carName = readLine()
            return carName?.split(",") ?: throw IllegalArgumentException("올바르게 다시 입력해주세요")
    }

    private fun tryCount():Int{
        println("시도할 횟수는 몇 회인가요?")
        val tryCountInput = readLine()!!.toInt()
        return tryCountInput
    }

    private fun moveCar(carNameInt: List<String>, carMove: MutableMap<String,Int>){
        for (car in carNameInt) {
            if ((0..9).random() >= 4) {
                carMove[car] = (carMove[car] ?: 0) + 1
            }
        }
        for (car in carNameInt) {
            val distance = carMove[car] ?: 0
            println("$car : ${"-".repeat(distance)}")
        }
    }
    private fun winnersList(carMove: MutableMap<String, Int>):List<String> {
        var maxMove = 0
        for ((_, distance) in carMove) {
            if (distance > maxMove) {
                maxMove = distance
            }
        }

        val winners = mutableListOf<String>()
        for ((car, distance) in carMove) {
            if (distance == maxMove) {
                winners.add(car)
            }
        }
        return winners
    }
    private fun printWinners(winners: List<String>){
        println("\n최종 우승자 : ${winners.joinToString(", ")}")
    }
}