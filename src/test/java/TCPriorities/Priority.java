package TCPriorities;

public enum Priority {
    // P0 : priorité critique — exécuter en premier, utiliser @Test(priority = 0)
    P0,
    // P1 : très haute priorité — utiliser @Test(priority = 1)
    P1,
    // P2 : haute priorité — utiliser @Test(priority = 2)
    P2,
    // P3 : priorité moyenne — utiliser @Test(priority = 3)
    P3,
    // P4 : priorité moyenne-basse — utiliser @Test(priority = 4)
    P4,
    // P5 : faible priorité — utiliser @Test(priority = 5)
    P5,
    // P6 : très faible / non critique — exécuter en dernier, utiliser @Test(priority = 6)
    P6
}
