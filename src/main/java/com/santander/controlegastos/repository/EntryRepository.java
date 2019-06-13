package com.santander.controlegastos.repository;

import com.santander.controlegastos.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByUser_idOrderByEntryDateDesc(final Long userId);

    List<Entry> findByUser_idAndEntryDate(final Long userId, final Date entryDate);

    Entry findByUser_idAndDescription(final Long userId, final String description);
}
