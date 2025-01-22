package br.com.everty.shared.utils.mappers

/**
 * Interface to be used whenever you need to map an object <From> to another object <To>
 * or a list of an object <From> to a list of an object <To>
 * @param From Original Object to be mapped
 * @param To Result object of the mapper
 */
interface Mapper<From, To> {
    fun toObject(fromObject: From): To

    fun toObjectList(fromObjectList: List<From>): List<To> {
        return fromObjectList.map { toObject(it) }
    }
}


