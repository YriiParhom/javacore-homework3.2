import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Main {
    public static void main(String[] args) throws IOException {
        GameProgress gp1 = new GameProgress(94, 10, 2, 254.32);
        GameProgress gp2 = new GameProgress(85, 3, 5, 315.47);
        GameProgress gp3 = new GameProgress(73, 7, 7, 426.75);

        try (FileOutputStream fos = new FileOutputStream(gp1.saveGame("save1.dat"))) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gp1);
        } catch (IOException ex) {
            System.out.println("Сохранение игры не произошло");
        }

        try (FileOutputStream fos = new FileOutputStream(gp2.saveGame("save2.dat"))) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gp2);
        } catch (IOException ex) {
            System.out.println("Сохранение игры не произошло");
        }

        try (FileOutputStream fos = new FileOutputStream(gp3.saveGame("save3.dat"))) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gp3);
        } catch (IOException ex) {
            System.out.println("Сохранение игры не произошло");
        }

        String[] fileUrl = {"D://Games/SaveGames/save1.dat", "D://Games/SaveGames/save2.dat", "D://Games/SaveGames/save3.dat"};

        zipFiles("D://Games/SaveGames/saves.zip", fileUrl);

        deleteFile("D://Games/SaveGames/save1.dat");
        deleteFile("D://Games/SaveGames/save2.dat");
        deleteFile("D://Games/SaveGames/save3.dat");

    }

    public static void zipFiles(String zipUrl, String[] fileUrl) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipUrl));
        ) {
            for (int i = 0; i < fileUrl.length; i++) {
                File file = new File(fileUrl[i]);

                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                zout.write(Files.readAllBytes(file.toPath()));
                zout.closeEntry();
            }
        } catch (IOException ex) {
            System.out.println("Не удалось создать архив");
        }
    }

    public static void deleteFile(String fileURL) {
        var file = new File(fileURL);
        if (file.delete()) {
            System.out.println("Файл " + fileURL + " успешно удален");
        } else {
            System.out.println("Удаление " + fileURL + " не произошло");
        }
    }

}
