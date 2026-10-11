package view;

import java.util.Scanner;

// Interface: kontrak untuk semua View yang punya sub-menu sendiri.
// Diimplementasikan oleh OrangTuaView, AnakView, dan CatatanHarianView.
public interface MenuView {
    void tampilkanMenu(Scanner scanner);
}
