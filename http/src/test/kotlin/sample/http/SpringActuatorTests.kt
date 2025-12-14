package sample.http

import io.kotest.core.spec.style.ExpectSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class SpringActuatorTests(private val mockMvc: MockMvc) :
  ExpectSpec({
    context("Spring Actuator Health Check") {
      expect("애플리케이션이 정상적으로 구동 중일 때 /health 엔드포인트가 200 OK를 반환한다") {
        val healthPath = "/health"
        val result = mockMvc.perform(
          MockMvcRequestBuilders.get(healthPath),
        )
        result.andExpect(MockMvcResultMatchers.status().isOk)
      }
    }

    context("Spring Actuator Info Check") {
      expect("애플리케이션의 정보가 /info 엔드포인트에서 제공된다") {
        val infoPath = "/info"
        val result = mockMvc.perform(
          MockMvcRequestBuilders.get(infoPath),
        )
        result.andExpect(MockMvcResultMatchers.status().isOk)
      }
    }
  })
