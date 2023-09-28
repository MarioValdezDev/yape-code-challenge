package mx.mariovaldez.yapecodechallenge

import mx.mariovaldez.yapecodechallenge.ktx.getId
import org.junit.Assert
import org.junit.Test

class IdTest {

    @Test
    fun check_if_id_not_contains_underscore() {
        val text = "23432_dimasom34234"
        val expected = "dimasom34234"
        Assert.assertEquals(expected, text.getId())
    }
}
