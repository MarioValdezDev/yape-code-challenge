package mx.mariovaldez.yapecodechallenge.ktx

fun String.getId() = this.substring(this.lastIndexOf("_") + 1)
