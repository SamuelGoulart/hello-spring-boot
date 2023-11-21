package br.com.hello.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnnouncementDto {

    private int announcementId;
    private String userName;
    private String title;
    private String description;
    private String imageAnnouncement;
    private String listingTitle;
    private int displayNumber;
    private String category;
    private String date;
    private String time;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static AnnouncementDto fromEntity(Announcement announcement) {
        User user = announcement.getUser();
        JsonNode payloadNode = readPayloadNode(announcement.getPayload());

        return new AnnouncementDto(
                announcement.getAnnouncementId(),
                user.getName(),
                announcement.getSubject(),
                announcement.getDescription(),
                getImageAnnouncement(payloadNode),
                announcement.getListingTitle(),
                announcement.getDisplayNumber(),
                announcement.getCategory(),
                announcement.getDate(),
                announcement.getTime()
        );
    }

    private static String getImageAnnouncement(JsonNode payloadNode) {
        if (payloadNode.has("imageAnnouncement")) {
            return payloadNode.get("imageAnnouncement").asText();
        }
        return null;
    }

    private static JsonNode readPayloadNode(String payload) {
        try {
            return objectMapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON in AnnouncementDto", e);
        }
    }
}
