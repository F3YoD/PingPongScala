import controllers.PingPongController
import model.Player
import org.scalatest.funsuite.AnyFunSuite
import play.api.test.Helpers
import services.PingPongService

import java.time.LocalDate
import scala.List
import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer

class ExampleSpec extends AnyFunSuite {
  test("Ping should return Pong") {
    assert(PingPongService.pong() == "Pong")
  }

  test("Get players return a list type") {
    val players = PingPongService.players()
    assert(players.isInstanceOf[ListBuffer[Player]])
  }

  test("Get player should return a non empty list"){
    val players : ListBuffer[Player] = PingPongService.players()
    assert(players.length > 0)
  }

  test("Post player should return a list with the new player added"){
    val players : ListBuffer[Player] = PingPongService.players()
    val newPlayer = new Player("p","f",2,LocalDate.now());
    players.addOne(newPlayer)
    PingPongService.addPlayer(newPlayer)
    assert(PingPongService.players().equals(players))
  }

  test("Get with filtered param should only return the rights data"){
    PingPongService.addPlayer(new Player("new", "player", 999, LocalDate.now))
    assert(PingPongService.filterFromVictory(999).length == 1)
  }
}