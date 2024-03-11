package ua.gov.diia.ui_base.components.subatomic.preview


val PreviewBase64Icons = PreviewBase64IconsContainer()

class PreviewBase64IconsContainer(
    val visa: String = PreviewBase64Images.visa,
    val mastercard: String = PreviewBase64Images.mastercard,
    val maestro: String = PreviewBase64Images.maestro,
    val discover: String = PreviewBase64Images.discover,
    val bottle: String = PreviewBase64Images.bottle,
    val apple: String = PreviewBase64Images.apple,
    val alphaBank: String = PreviewBase64Images.alphaBank,
    val cardTypeMC: String = PreviewBase64Images.cardTypeMC,
    val arrowNext: String = PreviewBase64Images.arrowNext,
    val sign: String = PreviewBase64Images.sign,
    val arrowEndTop: String = PreviewBase64Images.arrowRightTop
)

object PreviewBase64Images {

    const val visa: String =
        "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"
    const val mastercard: String =
        "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"
    const val maestro: String =
        "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"
    const val discover: String =
        "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"
    const val bottle: String =
        "iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsTAAALEwEAmpwYAAAB3klEQVR4nL3Uv0sjQRQH8GjhD7RURPCKu9JC8C/QP0IFbc7iuE60FRNQrDS9PwoRVPBHIYog3jWip4LiJm42vzbr6iSKETOZnVk9f6F5h8iOQZ1kE+M9eO33w7x5Mw5Hlmrr7jNbunrB6k6Xm/d31/Afx0errcfJhIDTvekoREkHPrAaG4x3QcL/O9A/OsVH9CnAwNj05wK40COKIFQvAs5Ms/pj4bre4guErkRAgrBdQkhDzsEAUBxStd8eWeHB7wHPCH3AhPYCQJFtIKBGpNfBkgDgTajbVnhI00dE4VImwGBwkaTtGcN1Xa/x+vyP+QLYoPF4PF4hBMLa4YwV5JVlQOsdEN4bB//+IkR/NQFaKIPUVg3cKD/BON8FdrIKfyODgEmSI0nD/CEE/MHwhQUcbjvhaK7kTcNG6Zu+PJ5IP8WKEPD6FD6e2FKdbeB+v/llqwx6KgSkg5e1RAvltoHUdm362t4JAY+spAoAYCEgB4LXfETLX/IaESbMIwRCqrbHL3nHlcMlT6Y/uCEhoB1FW9PXNLraCMfzGYDNSriV2wETYo3nMevfFAir0fwfGpvNGP5UCKGvsj94nyuQMBgyTbMqK/BUkVisSQmqCTu/KTbodYKwtXNKv70X9g9o6fnYYl/d7gAAAABJRU5ErkJggg=="
    const val apple: String =
        "iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAIvSURBVHgBtVY9bNNAGH2248QprYBUKdAiBRQk6MTC4HYvokNBgp2RlbVCAgZ2NsRGUUZgQCxILEgMDKViIeKnihRUQQEllZtYIf4523zXuE0CPmNXzZPe2ec7v/fd98m+A/rQiQ+INaJBdIlBQjJim/iBuEI8g7+wEIoGB8Qm8eKueJnYiJooj+UCSVX2a8ID1hVq7hPnEYFTt65g5u5ljC+eA7MsOOsNpIBGtDPUXBDNsH9s4+jUBDCdxwn9GqSChnblPVLgkkxNSTTafL4Gp2n2OrToQ0uzSIlJnqJ7RDlq1OvY2H5VBVQFXtcGM7vovPiEFFClXmyjQyZuUDtdxMk7S1DPH4OTYbC2WmB5H+x4Fm6W+u++w7n5BvhqCjWEK8hNFzD76Abk0sSOuKMyuEQ707vyPn/ubhpgiy+B9VakgSxyLi9fhUYmkSENojQOPJwXyQgMJAmFubP4X3n2vPQi5VNJbpCbOgxZUfBvuMPYsx+jUha15AYB85EEQ/aOn9zA2WrDaXciX+iHHfRvGxbwq5vcgMOsbiC+BgPxr4r/UbJI5dvj14isQVRZKjXEGUQmr7VWg/H2M+Kw8xE9rQNP6qIpHjcQfob128/gbBqiYQQbJvzlVcTgNw+Cr68smpGdOYLJ6zq8vATrpwE/Qxkt5sBkD07lI/wvRpxBlTd8Hw5GxBVuwDd7YwTijcHMLECwL++TRqg5hHK4JH704EcQlkLQDUVrYcrndkX/AKYTJeStgRtiAAAAAElFTkSuQmCC"
    const val alphaBank: String =
        "iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAG/SURBVHgBtZW9LwRBGMafdywaxLUUNnFq5yO0FMpL0BEJDS3+Eic6lUJ0RNCRuJYgdx0JsRIfldwmrnEfM2Zm3eUibmdyt/dLJjOZeT8mz747L6GGXCI+xZmYZaABCJEIdslFKMKTNr5c+JxElvFyKpbxvMopVYOPxrcgsIEoIKRid4+bwTLq4H+SUC7huiDnGa1AYJpxtEV78xo45PdkRMM2xqyvHz1naT2ou8fGRapELpNzr42xMzapk6jRkZyzcQGRGJYJhFWCjuQ8+Psb+Mcr2qdmYAsz13kgjzM+iWL6HMXLC722kymQyIiSR1E4PdQJFJ2LyzaucGyMOhdX9FwrTZB0x+hLuZG4CDOoVM9/5FeXULq9Qqg/DFRu/7WQhD86pIcKrHDGJ0zuZokKJ0d6Lj/cV/fUrb8P9lC6uTa5myVqFqsqagbn9z136xl07e4jjPzaUsip8IzfQP1UjUO+Ix+MjHxW3XomqmoaRsBnnIsXtAj5XGeZ7L/HaBEM5RSLZR7TXIhtRI2MqZp/tel/JgZTsvmsIwp08CfdKal2X/Vn1UKlbKrL9epeQeQagnnBQhYLkJUjrVSpHP8AlO+U4S4V0wMAAAAASUVORK5CYII="
    const val cardTypeMC: String =
        "iVBORw0KGgoAAAANSUhEUgAAAB4AAAAUCAYAAACaq43EAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAIgSURBVHgBzZbBbtNAEIb/jbeOYyLqioBccXEF90JfAHHiTJ+AXpAqJOgjNCeucOJY9QWoeAJ4A3LhBupeIlJAbaiiNE53s8y6rhO8Sb2cyn+xNZ6Zb3fWs7vANYlVOXxHnHAgMe/0FCvoibLP2V5sHonM/RhUv7n1s4N/BZ8giiTCVwxsRwNR6bOgoN0Wuvt6L4pG5AeNZ5QqKfuR/RPGqt3YtgfLbOjdBzTyA+SjXyTvphTha0A3+JV+pENPYtN/3u0sBOfQzxWJUGtKLD/5hVoLGK+3oANeFaIJvjELr02hcZLPtFIG6jUV2EjB/3LsEsIUx3uzNBb4HN4uKsprVL83zKBFxgFF9oZw0NpwEu5YYKr5I5fo4L4N8Y6cwPAYe/kX+AetLRxma7QUp5at1k/hItMhZ+/ipABT30UugbWZEpdl1ttFkvMp+DqUgaVpdgdNBt7Cbzrw4KI6T/sFePViG+y7BJ736pZtsuzDTVr4+VY6U2r91iV09C20bCq+ARdpsA+X7wWYw3+jaURVwenXEPJ4aZqMSqziENXSh0rNAa9AUKnZU4cMOP14K1tvAx2v3670pzY6UZJtNvmg2DKtQ+Kip/UBnUzJVcncDwmaqYGWDgmrne6g21GYPKZR7s8vvbGx9u/T9GHw4miNprOFOX7a/KyatQM23ChDjSovAqYCNPtsg5HgYhVClH0uLwK0OySZQXLR2Lb9/gv9AbzQwTvjp/GaAAAAAElFTkSuQmCC"
    const val arrowNext: String =
        "iVBORw0KGgoAAAANSUhEUgAAABEAAAAOCAYAAADJ7fe0AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAABwSURBVHgBrZHRCYAwDAWf4ACOkBHcSDfQDXQDR9CNHMERHKEmkID4UaGvB/dRWg6aAP+IOoHkUpO6gGD0iLmBoFdvD+0gsFB87VQ7FCKfkMRFIrSgtH7I0aAyAnIu7w3RgQMFDGzAiBmsIBB1zj14ADaqLdhwpVRoAAAAAElFTkSuQmCC"
    const val sign: String =
        "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"
    const val arrowRightTop: String =
        "iVBORw0KGgoAAAANSUhEUgAAAGAAAABgBAMAAAAQtmoLAAAAGFBMVEUAAADi7PTf7+/f6u/f6e/i7PTi7PTi7PQumukWAAAAB3RSTlMA7zAwIL+v/gVJxgAAALZJREFUWMPt1bEJwzAQhWETvIETVGeDjBCPkMojqE6TWz8IYR7Y4Lu/UHevEFzx8+HKUy6Xy+VGb/7YaZfBajCoNDAaFBpsNvqj5wqC424FBk8JCDAKGAWMAN/2EGBpDwFe7SXA1F4C9AAACmJADwCgIAYoCAIKYoCCIKAgBigAgAIfUEAABT6ggAAKfEABAfxgFRALqoBY0AEQlA7o9oJtB3T/vI9eDvfd+T883qc7l8vlcoP3B+qgxOScdh0fAAAAAElFTkSuQmCC"
}