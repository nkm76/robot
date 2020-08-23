package fun.robot.app;

import fun.robot.*;

import java.util.*;

public class RobotApp {

    private final Set<Position> scent = new HashSet<>();

    public static void main(String args[]) {
        RobotApp app = new RobotApp();
        app.init();
    }

    private void init() {
        System.out.println("Input the Mars grid, robot position and instructions; Enter blank line and hit enter to finish...");
        Scanner scanner = new Scanner(System.in);
        List<Robot> robots = new ArrayList<>();
        if (scanner.hasNextLine()) {
            Coordinates grid = scanGrid(tokens(scanner));
            while (true) {
                Position position = scanPosition(tokens(scanner));
                if (position != null) {
                    Robot robot = new Robot(grid, position, scent);
                    robots.add(robot);
                    List<Instruction> instructions = scanInstructions(tokens(scanner));
                    if (instructions != null) {
                        instructions.stream().forEach(robot::instruct);
                    } else {
                        System.out.println("Exiting! no instructions entered for Robot at: " + robot.position());
                        break;
                    }
                } else {
                    System.out.println("Exiting! no more robot positions entered");
                    break;
                }
            }
        }
        System.out.println("Output");
        robots.stream().forEach(r -> System.out.println(r.position().coordinates().x() + " " + r.position().coordinates().y() +
                " " + r.position().orientation().name() + (r.isLost() ? " LOST" : "")));
    }

    private String[] tokens(Scanner scanner) {
        String line;
        if ((line = scanner.nextLine()) != null && !line.trim().isEmpty()) {
            return line.split("\\s");
        }
        return null;
    }

    private Coordinates scanGrid(String[] tokens) {
        return tokens != null ? Coordinates.of(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1])) : null;
    }

    private Position scanPosition(String[] tokens) {
        return tokens != null ? Position.of(Coordinates.of(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1])), Orientation.valueOf(tokens[2])) : null;
    }

    private List<Instruction> scanInstructions(String[] tokens) {
        return Instruction.parse(tokens[0]);
    }

}
