package kr.imcf.ott.streaming;

import kr.imcf.ott.common.Message;
import kr.imcf.ott.domain.entity.Streaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StreamingResponse extends Message {
    private StreamingDTO result;
}
