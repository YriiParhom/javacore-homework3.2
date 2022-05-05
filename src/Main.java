import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Main {
    public static void main(String[] args) throws IOException {

        serialize(new GameProgress(94, 10, 2, 254.32), "save1.dat");
        serialize(new GameProgress(85,3,5,315.47), "save2.dat");
        serialize(new GameProgress(73,7,7,426.75), "save3.dat");

        String separator = File.separator;

        String save1 = "D:" + separator + separator + "Games" + separator + "SaveGames" + separator + "save1" +
                ".dat";
        String save2 = "D:" + separator + separator + "Games" + separator + "SaveGames" + separator + "save2" +
                ".dat";
        String save3 = "D:" + separator + separator + "Games" + separator + "SaveGames" + separator + "save3" +
                ".dat";

        String[] fileUrl = {save1, save2, save3};

        zipFiles("D:" + separator + separator+ "Games" + separator + "SaveGames" + separator + "saves.zip", fileUrl);

        deleteFile(save1);
        deleteFile(save2);
        deleteFile(save3);

    }

    public static void serialize (GameProgress gp, String name){
        try (FileOutputStream fos = new FileOutputStream(gp.saveGame(name))) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gp);
        } catch (IOException ex) {
            System.out.println("Сохранение игры не произошло");
        }
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
