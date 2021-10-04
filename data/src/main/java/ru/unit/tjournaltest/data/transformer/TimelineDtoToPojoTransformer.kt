package ru.unit.tjournaltest.data.transformer

import ru.unit.tjournaltest.data.api.dto.*
import ru.unit.tjournaltest.domain.timeline.pojo.*
import javax.inject.Inject

class TimelineDtoToPojoTransformer @Inject constructor() : BaseTransformer<TimelineResponseDTO, TimelinePOJO> {
    override fun transform(value: TimelineResponseDTO): TimelinePOJO {
        val result = value.result
        return if (result != null) {
            TimelinePOJO(result.lastId ?: "", result.lastSortingValue ?: "", result.items.mapNotNull { item ->
                val data = item.data
                if (data is DataTypeEntryDTO) {
                    TimelineItemPOJO(
                        data.id,
                        data.subsite?.name,
                        transform(data.author?.avatar),
                        data.author?.name,
                        data.title,
                        data.date,
                        data.counters.comments,
                        data.likes.counter,
                        transform(data.blocks)
                    )
                } else {
                    null
                }
            })
        } else {
            TimelinePOJO("", "", emptyList())
        }
    }

    private fun transform(value: DataDTO?): TimelineTypeImagePOJO? {
        val data = value?.data
        return if (data is DataTypeImageDTO) {
            TimelineTypeImagePOJO(
                data.type,
                data.uuid
            )
        } else {
            null
        }
    }

    private fun transform(value: List<BlockDTO>): TimelineCoverPOJO? {
        val block = value.firstOrNull()
        return if (block == null) {
            null
        } else {
            val blockData = block.data
            val text = (blockData as? BlockTypeTextDTO)?.let { transform(it) }
            val media = (blockData as? BlockTypeMediaDTO)?.let { transform(it) }
            val video = (blockData as? BlockTypeVideoDTO)?.let { transform(it) }

            TimelineCoverPOJO(
                block.type,
                text,
                video,
                media
            )
        }
    }

    private fun transform(value: BlockTypeTextDTO): TimelineTypeTextPOJO? {
        return if (value.text == null) {
            null
        } else {
            TimelineTypeTextPOJO(value.text)
        }
    }

    private fun transform(value: BlockTypeMediaDTO): TimelineTypeImagePOJO? {
        value.items.forEach { item ->
            val image = item.imageData
            if (image != null && image.data is DataTypeImageDTO) {
                return transform(image)
            }
        }
        return null
    }

    private fun transform(value: BlockTypeVideoDTO): TimelineTypeVideoPOJO? {
        val video = value.video
        return if (video == null || video.data !is DataTypeVideoDTO) {
            null
        } else {
            val externalService = transform(video.data.externalService)
            if (externalService == null) {
                null
            } else {
                TimelineTypeVideoPOJO(
                    transform(video.data.dataImage),
                    value.title,
                    externalService
                )
            }
        }
    }

    private fun transform(value: ExternalServiceDTO?): TimelineExternalServicePOJO? {
        return if (value?.name != null && value.id != null) {
            TimelineExternalServicePOJO(value.name, value.id)
        } else {
            null
        }
    }
}