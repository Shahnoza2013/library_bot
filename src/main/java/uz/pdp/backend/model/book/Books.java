package uz.pdp.backend.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.backend.enums.FileFormat;
import uz.pdp.backend.enums.Genre;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Books {
    private Long id;
    private String name;
    private Genre genreBooks;
    private String author;
    private int pageOfBook;
    private FileFormat format;


}
