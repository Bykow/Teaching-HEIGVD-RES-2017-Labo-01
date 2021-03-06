package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    //we should always visit root
    visitor.visit(rootDirectory);

    if (rootDirectory.isDirectory()) {
      File[] list = rootDirectory.listFiles();
      //Next dir in root to explore recursively
      ArrayList<File> dir = new ArrayList<>();

      //needs to be sorted
      Arrays.sort(list);

        for (File f : list) {
          if (f.isFile()){
            explore(f, visitor);
          }
          if (f.isDirectory()) {
            dir.add(f);
          }
        }

        //exploration of dirs in rootDir
        for (File f : dir) {
          explore(f, visitor);
        }

    }
  }
}
