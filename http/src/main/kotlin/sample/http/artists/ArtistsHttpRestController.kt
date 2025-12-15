package sample.http.artists

import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.LinkRelation
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.mediatype.hal.HalModelBuilder
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ArtistsHttpRestController {

  @GetMapping("/artists", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getArtists(): ResponseEntity<List<ArtistResponse>> = ResponseEntity.ok(
    listOf(
      ArtistResponse("1", "The Beatles"),
      ArtistResponse("2", "Led Zeppelin"),
      ArtistResponse("3", "Pink Floyd"),
    ),
  )

  @GetMapping("/artists", produces = [MediaTypes.HAL_JSON_VALUE])
  fun getArtistsHateoas(): ResponseEntity<RepresentationModel<*>> {
    val artists = listOf(
      ArtistResponse("1", "The Beatles"),
      ArtistResponse("2", "Led Zeppelin"),
      ArtistResponse("3", "Pink Floyd"),
    )

    return ResponseEntity.ok(
      HalModelBuilder.halModel()
        .embed(
          artists.map {
            EntityModel.of(it)
              .add(linkTo<ArtistsHttpRestController> { getArtistById(it.id) }.withSelfRel())
          },
          LinkRelation.of("artists"),
        )
        .build(),
    )
  }

  @GetMapping("/artists/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getArtistById(@PathVariable id: String): ResponseEntity<ArtistResponse> {
    val artists = when (id) {
      "1" -> ArtistResponse("1", "The Beatles")
      "2" -> ArtistResponse("2", "Led Zeppelin")
      "3" -> ArtistResponse("3", "Pink Floyd")
      else -> throw IllegalArgumentException("Artist not found")
    }

    return ResponseEntity.ok(artists)
  }

  @GetMapping("/artists/{id}", produces = [MediaTypes.HAL_JSON_VALUE])
  fun getArtistByIdHateoas(@PathVariable id: String): ResponseEntity<EntityModel<ArtistResponse>> {
    val artist = when (id) {
      "1" -> ArtistResponse("1", "The Beatles")
      "2" -> ArtistResponse("2", "Led Zeppelin")
      "3" -> ArtistResponse("3", "Pink Floyd")
      else -> throw IllegalArgumentException("Artist not found")
    }

    val artistModel = EntityModel.of(artist)
      .add(linkTo<ArtistsHttpRestController> { getArtistByIdHateoas(id) }.withSelfRel())

    return ResponseEntity.ok(artistModel)
  }
}
