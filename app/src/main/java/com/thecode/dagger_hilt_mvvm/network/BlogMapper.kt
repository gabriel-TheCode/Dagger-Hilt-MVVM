package com.thecode.dagger_hilt_mvvm.network

import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.util.EntityMapper
import javax.inject.Inject

class BlogMapper
@Inject
constructor() : EntityMapper<BlogObjectResponse, Blog> {
    override fun mapFromEntity(entity: BlogObjectResponse): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogObjectResponse {
        return BlogObjectResponse(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogObjectResponse>): List<Blog> {
        return entities.map { mapFromEntity(it) }
    }

}
