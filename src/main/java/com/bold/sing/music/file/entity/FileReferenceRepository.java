package com.bold.sing.music.file.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileReferenceRepository extends JpaRepository<FileReference, UUID> {

}