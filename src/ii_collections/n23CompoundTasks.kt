package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    return this.customers.filter { customer -> customer.orderedProducts.contains(product) }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    return this.orders.filter { order -> order.isDelivered } .map { order -> order.products }.flatMap { it -> it.toList() }.maxBy { product -> product.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    return this.customers.sumBy {
        customer -> customer.orders.sumBy { order -> order.products.count{ p -> p == product} }
    }
}
