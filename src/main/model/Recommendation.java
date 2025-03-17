package model;

public class Recommendation {
    private String category;
    
    // Priority level (1-3, where 1 is highest priority)
    private int priority;
    
    // Description of the recommended action
    private String description;
    
    // Risk factor this recommendation addresses
    private String targetRiskFactor;
    
    /**
     * Constructor for creating a new recommendation.
     * 
     * @param category Category of recommendation (e.g., "Biomechanical", "Training", "Equipment")
     * @param priority Priority level (1=highest, 3=lowest)
     * @param description Detailed description of the recommended action
     * @param targetRiskFactor Specific risk factor being addressed
     */
    public Recommendation(String category, int priority, String description, String targetRiskFactor) {
        this.category = category;
        this.priority = priority;
        this.description = description;
        this.targetRiskFactor = targetRiskFactor;
    }
    
    /**
     * Creates a string representation of the priority level.
     * 
     * @return String representation of priority
     */
    public String getPriorityText() {
        switch (priority) {
            case 1:
                return "High";
            case 2:
                return "Medium";
            case 3:
                return "Low";
            default:
                return "Unknown";
        }
    }
    
    // Formats recommendation as string
    @Override
    public String toString() {
        return "[" + getPriorityText() + " Priority] " + category + ": " + description + 
               " (Addresses: " + targetRiskFactor + ")";
    }
    
    // Getters
    
    public String getCategory() {
        return category;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getTargetRiskFactor() {
        return targetRiskFactor;
    }
}
