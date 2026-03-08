package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates tickets.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - creates partially valid objects
 * - mutates after creation (bad for auditability)
 * - validation is scattered & incomplete
 *
 * TODO (student):
 * - After introducing immutable IncidentTicket + Builder, refactor this to stop mutating.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // scattered validation (incomplete on purpose)
    

       IncidentTicket t = new IncidentTicket.builder()
                .setId(id)
                .setReporterEmail(reporterEmail)
                .setTitle(title)
                .setPriority("MEDIUM")
                .setSource("CLI")
                .setCustomerVisible(false)
                .setTags(List.of("NEW"))
                .build();

        // BAD: mutating after creation
        // t.setPriority("MEDIUM");
        // t.setSource("CLI");
        // t.setCustomerVisible(false);


        // List<String> tags = new ArrayList<>();
        // tags.add("NEW");
        // t.setTags(tags);


        return t;
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        // BAD: mutating ticket after it has been "created"
        // t.setPriority("CRITICAL");
        // t.getTags().add("ESCALATED"); // list leak
        IncidentTicket updated = t.tobuilder().setPriority("CRITICAL").addTag("ESCALATED").build();
        return updated;
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        // scattered validation
        // if (assigneeEmail != null && !assigneeEmail.contains("@")) {
        //     throw new IllegalArgumentException("assigneeEmail invalid");
        // }
        // t.setAssigneeEmail(assigneeEmail);
        IncidentTicket updated = t.tobuilder().setAssigneeEmail(assigneeEmail).build(); 
        return updated;
    }
}
