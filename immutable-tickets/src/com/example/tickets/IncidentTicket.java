package com.example.tickets;

import java.util.ArrayList;
import java.util.*;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority; // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags; // mutable leak
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes; // optional
    private final String source; // e.g. "CLI", "WEBHOOK", "EMAIL"

    private IncidentTicket(builder b) {
        this.id = b.id;
        this.reporterEmail = b.reporterEmail;
        this.title = b.title;
        this.description = b.description;
        this.priority = b.priority;
        this.tags = Collections.unmodifiableList(new ArrayList<>(b.tags)); // FIXED: immutable
        this.assigneeEmail = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes = b.slaMinutes;
        this.source = b.source;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public List<String> getTags() {
        return new ArrayList<>(tags);
    } 

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public boolean isCustomerVisible() {
        return customerVisible;
    }

    public Integer getSlaMinutes() {
        return slaMinutes;
    }

    public String getSource() {
        return source;
    }

    static class builder {
        private String id;
        private String reporterEmail;
        private String title;

        private String description;
        private String priority; // LOW, MEDIUM, HIGH, CRITICAL
        private List<String> tags = new ArrayList<>(); // mutable leak
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes; // optional
        private String source;

        public builder setId(String id) {
            this.id = id;
            return this;
        }

        public builder setReporterEmail(String reporterEmail) {
            this.reporterEmail = reporterEmail;
            return this;
        }

        public builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public builder setPriority(String priority) {
            this.priority = priority;
            return this;
        }

        public builder setTags(List<String> tags) {
            this.tags = new ArrayList<>(tags);
            return this;
        } // defensive copy

        public builder setAssigneeEmail(String assigneeEmail) {
            this.assigneeEmail = assigneeEmail;
            return this;
        }

        public builder setCustomerVisible(boolean customerVisible) {
            this.customerVisible = customerVisible;
            return this;
        }

        public builder setSlaMinutes(Integer slaMinutes) {
            this.slaMinutes = slaMinutes;
            return this;
        }

        public builder setSource(String source) {
            this.source = source;
            return this;
        }

        public IncidentTicket build() {
            // Validation can be added here
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            Validation.requireMaxLen(title, 80, "title");
            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");
            if (assigneeEmail != null) {
                Validation.requireEmail(assigneeEmail, "assigneeEmail");
            }
            return new IncidentTicket(this);
        }

        public builder addTag(String tag) {
            Validation.requireNonBlank(tag, "tag");
            this.tags.add(tag);
            return this;
        }
    }

    public builder tobuilder() {
        return new builder()
                .setId(this.id)
                .setReporterEmail(this.reporterEmail)
                .setTitle(this.title)
                .setDescription(this.description)
                .setPriority(this.priority)
                .setTags(this.tags) // defensive copy in setter
                .setAssigneeEmail(this.assigneeEmail)
                .setCustomerVisible(this.customerVisible)
                .setSlaMinutes(this.slaMinutes)
                .setSource(this.source);
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}
