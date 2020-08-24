package com.georgidinov.recipesapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

    //== fields ==
    private Long id;
    private String recipeNotes;

}//end of class NotesCommand
