package com.bold.sing.music.songmusic.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SongGenre {
    ART_PUNK("Art Punk"),
    ALTERNATIVE_ROCK("Alternative Rock"),
    BRITPUNK("Britpunk"),
    COLLEGE_ROCK("College Rock"),
    CROSSOVER_THRASH("Crossover Thrash"),
    CRUST_PUNK("Crust Punk"),
    EMOTIONAL_HARDCORE("Emotional Hardcore"),
    EXPERIMENTAL_ROCK("Experimental Rock"),
    FOLK_PUNK("Folk Punk"),
    GOTHIC_ROCK("Gothic Rock"),
    GRUNGE("Grunge"),
    HARDCORE_PUNK("Hardcore Punk"),
    HARD_ROCK("Hard Rock"),
    INDIE_ROCK("Indie Rock"),
    LO_FI("Lo-fi"),
    MUSIQUE_CONCRETE("Musique Concrète"),
    NEW_WAVE("New Wave"),
    PROGRESSIVE_ROCK("Progressive Rock"),
    PUNK("Punk"),
    SHOEGAZE("Shoegaze"),
    STEAMPUNK("Steampunk");

    private final String displayName;
}
