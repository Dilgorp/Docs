package ru.dilgorp.docs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.dilgorp.docs.model.document.packinglist.PackingList
import ru.dilgorp.docs.model.document.packinglist.PackingListRow

@Repository
interface PackingListRepository: JpaRepository<PackingList, Long>

@Repository
interface PackingListRowRepository: JpaRepository<PackingListRow, Long>