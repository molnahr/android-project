from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='my_web-home'),
    path('about/', views.about, name='my_web-about'),
]