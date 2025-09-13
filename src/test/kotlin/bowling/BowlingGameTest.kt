package bowling

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
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
        rollMany(17, 0)

        assertThat(game.score()).isEqualTo(10 + 3 + 3)
    }

    @Test
    fun `one strike`() {
        rollStrike()
        game.roll(3)
        game.roll(4)
        rollMany(16, 0)

        assertThat(game.score()).isEqualTo(10 + 3 + 4 + 3 + 4)
    }

    @Test
    fun `perfect game`() {
        rollMany(12,10)

        assertThat(game.score()).isEqualTo(300)
    }

    @Test
    fun `all spares`() {
        repeat(21) {
            game.roll(5)
        }

        assertThat(game.score()).isEqualTo(150)
    }

    @Test
    fun `alternating strikes and misses`() {
        repeat(5) {
            rollStrike()
            game.roll(0)
            game.roll(0)
        }

        assertThat(game.score()).isEqualTo(50)
    }

    @Test
    fun `multiple strikes in a row`() {
        rollStrike()
        rollStrike()
        rollStrike()
        rollMany(14, 0)

        assertThat(game.score()).isEqualTo(60)
    }

    @Test
    fun `tenth frame spare`() {
        rollMany(18, 0)
        rollSpare()
        game.roll(5)

        assertThat(game.score()).isEqualTo(15)
    }

    @Test
    fun `tenth frame strike`() {
        rollMany(18, 0)
        rollStrike()
        game.roll(3)
        game.roll(4)

        assertThat(game.score()).isEqualTo(17)
    }

    @Test
    fun `spare in ninth frame with tenth frame strike`() {
        rollMany(16, 0)
        rollSpare()
        rollStrike()
        game.roll(5)
        game.roll(3)

        assertThat(game.score()).isEqualTo(38)
    }

    @Test
    fun `can roll maximum pins per frame`() {
        game.roll(10)
        game.roll(10)
        rollMany(16, 0)

        assertThat(game.score()).isEqualTo(30)
    }

    @Test
    fun `nine pin nine pin spare`() {
        game.roll(9)
        game.roll(1)
        game.roll(9)
        game.roll(1)
        rollMany(16, 0)

        assertThat(game.score()).isEqualTo(29)
    }

    private fun rollStrike() {
        game.roll(10)
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