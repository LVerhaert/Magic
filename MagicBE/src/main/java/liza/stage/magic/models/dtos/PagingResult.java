package liza.stage.magic.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class PagingResult<T> {
    public List<T> data;
    public int total;

    public PagingResult(List<T> data, int total) {
        this.data = data;
        this.total = total;
    }

    public PagingResult() {
        data = new ArrayList<>();
        total = 0;
    }
}
