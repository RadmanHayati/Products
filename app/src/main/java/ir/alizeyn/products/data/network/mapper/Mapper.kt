package ir.alizeyn.products.data.network.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}