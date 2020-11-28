from django.shortcuts import render, redirect
#automatikus mar meglevo django dolog
from django.contrib.auth.forms import UserCreationForm
from django.contrib import messages
from .forms import UserRegisterForm, UserUpdateForm, ProfileUpdateForm

#az volt a problema, hogyha beirom /profile megjelenik a profile(adatok nelkul, de meghivhato),ezert megkell csinaljuk, hogy csak akkor lehessen meghivni, ha be vagyunk jelentkezve
# vagy ha egyszeruen vissza gombra megyunk kijelentkezes utan akkor is vissza tudnank menni a profilra, de ezt le akarju tiltani (1*)
# a django ad erre lehetoseget es elek is vele.
from django.contrib.auth.decorators import login_required

#https://docs.djangoproject.com/en/3.1/ref/request-response/
# a szovegeket at tudom irni csak ra kell kattintani a UserCreationFormra
def register(request):
    if request.method == 'POST':
        form = UserRegisterForm(request.POST)
        #ez ellenorzi nekunk, hogy vane mar ilyen felhasznalonk az adatbazisban, s minden feltetel a nevre es jeszora be van-e tartatva.
        if form.is_valid(): 
             #automatikusan lementi az adatbazisba a dolgokat
            form.save()
            #ezek ahhoz kellenek, hogz majd a fooldalon kiirja, hogy sikeres a letrehozatal
            username = form.cleaned_data.get('username')
            messages.success(request, f'Az fiokod elkeszult! Kedves {username}, mostmar belephet!')
            return redirect('login')
    else:
        form = UserRegisterForm()
    return render(request,'users/register.html',{'form': form})

#ez ez decorator
#itt adds functionality to an existing function to our profile view, ahova be kell jelentkeznie
#eloszor nem tudja a django hol keresse, ezert hibat dob ki, ezert meg kell adjukneki a settings.py LOGIN_URL = 'login'-t s ide iranyit majd, nem hibat dob
#pl nem bejelentkezett allapotba, ha be akarunk lepni akkor nem tudja hova iranyitson, ezert hibat dob ki, de igy a loginhoz fog vezetni minket

#?next=/profile/ nekunk az is meg van oldva, hogy ugye a profilra akarunk menni, de elotte be kell jelentkezzunk
#s hogy ne kelljen bejelentkezes utan ujra ra menni a profilra ez automatikusan atiranyit.
@login_required
def profile(request):
    u_form = UserUpdateForm()
    p_form = ProfileUpdateForm()
    context = {
        'u_form': u_form,
        'p_form': p_form
    }
    return render(request,'users/profile.html', context)

# messages.debug
# messages.info
# messages.success
# messages.warning
# messages.error