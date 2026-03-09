public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        PowerControl pjPower = reg.getFirstOfType(Projector.class);
        InputConnectable pjInput = reg.getFirstOfType(Projector.class);

        pjPower.powerOn();
        pjInput.connectInput("HDMI-1");

        BrightnessControl lights = reg.getFirstOfType(BrightnessControl.class);
        lights.setBrightness(60);

        TemperatureControl ac = reg.getFirstOfType(TemperatureControl.class);
        ac.setTemperatureC(24);

        AttendanceScanning scanner = reg.getFirstOfType(AttendanceScanning.class);
        System.out.println("Attendance scanned: present=" + scanner.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstOfType(Projector.class).powerOff();
        reg.getFirstOfType(LightsPanel.class).powerOff();
        reg.getFirstOfType(AirConditioner.class).powerOff();
    }
}
