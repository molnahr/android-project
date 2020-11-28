from django.db import models
from django.contrib.auth.models import User

# Create your models here.
#adatbázis: 1-1 hez kapcsolat user-profilkép
class Profile(models.Model):
    #on_delete megmondja, hogy mit csinaljunk ha a user ugydont torli a kepet
    #models.CASCADE azt jelenti, ha a User torlodik akkor a Profil is, de ha a Profil torlodik, akkor nem fog a User.
    user = models.OneToOneField(User,on_delete=models.CASCADE)
    image = models.ImageField(default='default.jpg', upload_to='profile_pics')

    def __str__(self):
        return f'{self.user.username} Profile'

        # pip install Pillow --ez dolgozik a kepekkel pythonban (ImageField) miatt kell
        # .\manage.py makemigrations
        # .\manage.py migrate  

#regisztralnunk kell az admin.py, ahhoz hogy az admin oldalon tudjuk hasznalni.
#automatikusan letrehozodik a profile_pics, ami tartalmazza majd akepeket amiket feltoltenek a felhasznalok.
 