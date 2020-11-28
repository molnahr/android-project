
from django.shortcuts import render
from .models import Post


def home(request):
    context = {
        #lekerdezes ugyan az lenyegeben mint shellben volt
        'posts': Post.objects.all()
    }
    return render(request,'my_web/home.html',context)


def about(request):
    return render(request,'my_web/about.html',{'title': 'Valami'})