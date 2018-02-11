package com.rodrigolc.madridshops.domain.model

/**
 * Shoptivity: represents one Shoptivity
 */
data class Shoptivity(
        val id: Int,
        val name: String,
        val address: String,
        val description: String,
        val image: String,
        val logo: String,
        val openingHours: String,
        val longitude: Double?,
        val latitude: Double?,
        val type: String
        ) {
    init {
        Shoptivities(ArrayList<Shoptivity>())
    }
}


class Shoptivities(val shoptivities: MutableList<Shoptivity>): Aggregate<Shoptivity> {
    override fun count(): Int = shoptivities.size

    override fun all(): List<Shoptivity> = shoptivities

    override fun get(position: Int): Shoptivity = shoptivities.get(position)

    override fun add(element: Shoptivity) {
        shoptivities.add(element)
    }

    override fun delete(position: Int) {
        shoptivities.removeAt(position)
    }

    override fun delete(element: Shoptivity) {
        shoptivities.remove(element)
    }
}
