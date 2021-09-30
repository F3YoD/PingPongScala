package services

import model.{Page, Player}

import scala.collection.mutable.ListBuffer

object PaginationService {
    def pagination(page:Page,data: ListBuffer[Player]) = {
      data.slice(page.max * page.current,page.max * (page.current+1))
    }
}
