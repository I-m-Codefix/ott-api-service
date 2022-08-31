package kr.imcf.ott.streaming;

import kr.imcf.ott.domain.entity.Category;
import kr.imcf.ott.domain.entity.Streaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StreamingDTO {
    private Long id;
    private Long categoryId; // long으로 수정
    private String ottName;
    private String ottThumbnail;
    private String ottS3Id;
    private String ottDesc;
    private String ottDescDetail;
    private String playUri;
    private String playTime;
    private String status;
    private String regAdminId;
    private Long viewCount;
    private Long likeCount;
    private Long unlikeCount;

    public static StreamingDTO of(Streaming streaming) {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.createTypeMap(Streaming.class, StreamingDTO.class)
//                .addMapping(Streaming::getId, StreamingDTO::setId)
//                .addMapping(Streaming::getCategory, StreamingDTO::setCategory)
//                .addMapping(Streaming::getOttName, StreamingDTO::setOttName)
//                .addMapping(Streaming::getOttThumbnail, StreamingDTO::setOttThumbnail)
//                .addMapping(Streaming::getOttS3Id, StreamingDTO::setOttS3Id)
//                .addMapping(Streaming::getOttDesc, StreamingDTO::setOttDescDetail)
//                .addMapping(Streaming::getPlayUri, StreamingDTO::setPlayUri)
//                .addMapping(Streaming::getPlayTime, StreamingDTO::setPlayTime)
//                .addMapping(Streaming::getStatus, StreamingDTO::setStatus)
//                .addMapping(Streaming::getRegAdminId, StreamingDTO::setRegAdminId)
//                .addMapping(Streaming::getViewCount, StreamingDTO::setViewCount)
//                .addMapping(Streaming::getLikeCount, StreamingDTO::setLikeCount)
//                .addMapping(Streaming::getUnLikeCount, StreamingDTO::setUnlikeCount);

        return new StreamingDTO(
                streaming.getId(),
                streaming.getCategory().getId(),
                streaming.getOttName(),
                streaming.getOttThumbnail(),
                streaming.getOttS3Id(),
                streaming.getOttDesc(),
                streaming.getOttDescDetail(),
                streaming.getRegAdminId(),
                streaming.getPlayUri(),
                streaming.getPlayTime(),
                streaming.getStatus(),
                streaming.getViewCount(),
                streaming.getLikeCount(),
                streaming.getUnLikeCount()
        );
    }

}