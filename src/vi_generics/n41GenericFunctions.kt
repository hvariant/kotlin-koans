package vi_generics

import util.TODO
import java.util.*

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun <T, C: Collection<T>, MC: MutableCollection<T>> C.partitionTo(c1: MC, c2: MC, predicate: (T) -> Boolean) :Pair<C, C> {
    val (left, right) = partition(predicate)
    left.toCollection(c1)
    right.toCollection(c2)

    // MutableCollection<T> implements Collection<T>
    return Pair<C,C>(first=c1 as C,second=c2 as C)
}

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return partitionTo(ArrayList<String>(), ArrayList<String>()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    return partitionTo(HashSet<Char>(), HashSet<Char>()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}