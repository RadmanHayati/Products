package ir.alizeyn.products.data.network.common

interface Mapper<I, O> {
    fun map(input: I): O
}