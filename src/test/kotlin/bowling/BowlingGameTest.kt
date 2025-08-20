package bowling

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class BowlingGameTest {

    lateinit var game: Game

    @BeforeEach
    fun setUp() {
        game = Game()
    }

    @Test
    fun `gutter game`() {
        rollMany(20, 0)

        assertThat(game.score()).isEqualTo(0)
    }

    @Test
    fun `all ones`() {
        rollMany(20, 1)

        assertThat(game.score()).isEqualTo(20)
    }

    @Test
    fun `one spare`() {
        rollSpare()
        game.roll(3)
        rollMany(17,0)

        assertThat(game.score()).isEqualTo(10 + 3 + 3)
    }

    private fun rollSpare() {
        game.roll(5)
        game.roll(5)
    }

    private fun rollMany(times: Int, pins: Int) {
        repeat(times) {
            game.roll(pins)
        }
    }
}