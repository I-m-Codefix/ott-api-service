package kr.imcf.ott.streaming;

import kr.imcf.ott.common.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StreamingListResponse extends Message {
    private List<StreamingDTO> results;
}
