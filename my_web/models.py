from django.db import models
from django.utils import timezone

#kapcsolat, hogy a bejegyzes letrehozoja automatikusan meglegyen
from django.contrib.auth.models import User 

#bejegyzés  a fő oldalra.
class Post(models.Model):
    title = models.CharField(max_length=50)
    content = models.TextField() #nincs megszabva mennyi adat fér bele
    #tutorialban: date_posted = models.DateTimeField(default = timezone.now)
    date_posted = models.DateTimeField(auto_now_add=True)
    author = models.ForeignKey(User,on_delete=models.CASCADE)
# Create your models here.
    def __str__(self):
        return self.title
