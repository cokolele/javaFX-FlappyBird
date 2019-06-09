class Asset {
    private String path;
    private double width, height;

    public Asset(String path, double width, double height) {
        this.path = path;
        this.width = width;
        this.height = height;        
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public String getPath() {
        return path;
    }
}
