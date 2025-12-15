package sample.http.artists

import io.kotest.core.spec.style.BehaviorSpec
import org.hamcrest.CoreMatchers.containsString
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.hateoas.MediaTypes
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(value = [ArtistsHttpRestController::class])
class ArtistHttpRestControllerUnitTest(private val mockMvc: MockMvc) :
  BehaviorSpec({
    given("ArtistsHttpRestController") {
      `when`("GET /artists is called") {
        val result = mockMvc.get("/artists") {
          accept(MediaType.APPLICATION_JSON)
        }
        then("it should return the list of artists") {
          result
            .andDo { print() }
            .andExpect {
              status { isOk() }
              jsonPath("$[0].id") { value("1") }
              jsonPath("$[0].name") { value("The Beatles") }
              jsonPath("$[1].id") { value("2") }
              jsonPath("$[1].name") { value("Led Zeppelin") }
              jsonPath("$[2].id") { value("3") }
              jsonPath("$[2].name") { value("Pink Floyd") }
            }
        }
      }

      `when`("GET /artists/{id} is called with HAL JSON") {
        val result = mockMvc.get("/artists/1") {
          accept(MediaType.APPLICATION_JSON)
        }
        then("it should return the artist with HAL format") {
          result
            .andDo { print() }
            .andExpect {
              status { isOk() }
              jsonPath("$.id") { value("1") }
              jsonPath("$.name") { value("The Beatles") }
            }
        }
      }
    }

    given("HATEOAS") {
      `when`("GET /artists is called with HAL JSON") {
        val result = mockMvc.get("/artists") {
          accept(MediaTypes.HAL_JSON)
        }
        then("it should return the list of artists with HATEOAS links") {
          result
            .andDo { print() }
            .andExpect {
              status { isOk() }
              jsonPath("$._embedded.artists[0].id") { value("1") }
              jsonPath("$._embedded.artists[0].name") { value("The Beatles") }
              jsonPath("$._embedded.artists[0]._links.self.href") { containsString("/artists/1") }
              jsonPath("$._embedded.artists[1].id") { value("2") }
              jsonPath("$._embedded.artists[1].name") { value("Led Zeppelin") }
              jsonPath("$._embedded.artists[1]._links.self.href") { containsString("/artists/2") }
              jsonPath("$._embedded.artists[2].id") { value("3") }
              jsonPath("$._embedded.artists[2].name") { value("Pink Floyd") }
              jsonPath("$._embedded.artists[2]._links.self.href") { containsString("/artists/3") }
            }
        }
      }

      `when`("GET /artists/{id} is called with HAL JSON") {
        val result = mockMvc.get("/artists/1") {
          accept(MediaTypes.HAL_JSON)
        }
        then("it should return the artist with HATEOAS links") {
          result
            .andDo { print() }
            .andExpect {
              status { isOk() }
              jsonPath("$.id") { value("1") }
              jsonPath("$.name") { value("The Beatles") }
              jsonPath("$._links.self.href") { containsString("/artists/1") }
            }
        }
      }
    }
  })
