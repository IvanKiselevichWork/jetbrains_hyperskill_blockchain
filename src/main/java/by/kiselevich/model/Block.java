package by.kiselevich.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Block {
    private Long id;
    private Long timestamp;
    private String previousBlockHash;
}
