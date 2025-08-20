package bowling

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


// gutter game: 0
// all ones: 20
class BowlingGameTest {

    @Test
    fun `gutter game`() {
        val game = Game()

        repeat(20) {
            game.roll(0)
        }

        assertThat(game.score()).isEqualTo(0)
    }

    @Test
    fun `all ones`() {
        val game = Game()

        repeat(20) {
            game.roll(1)
        }

        assertThat(game.score()).isEqualTo(20)
    }
}