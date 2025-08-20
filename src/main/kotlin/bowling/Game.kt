package bowling

class Game {
    private val rolls = IntArray(21)
    private var currentRoll = 0

    fun roll(pins: Int) {
        rolls[currentRoll++] = pins
    }

    fun score(): Int {
        var score = 0
        var frameIndex = 0

        for(frame in 0 until 10) {
            if(isSpare(frameIndex)) {
                score += 10 + rolls[frameIndex+2]
                frameIndex += 2
            } else {
                score += rolls[frameIndex] + rolls[frameIndex + 1]
                frameIndex += 2
            }
        }
        return score
    }

    private fun isSpare(frameIndex: Int): Boolean = rolls[frameIndex] + rolls[frameIndex + 1] == 10
}
