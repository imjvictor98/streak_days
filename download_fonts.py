import urllib.request
import os

font_dir = "app/src/main/res/font"
os.makedirs(font_dir, exist_ok=True)

fonts = {
    "plusjakartasans": ["Regular", "Medium", "SemiBold", "Bold"],
    "hankengrotesk": ["Regular", "Medium", "SemiBold", "Bold"],
    "inter": ["Regular", "Medium", "SemiBold", "Bold"],
    "bevietnampro": ["Regular", "Medium", "SemiBold", "Bold"]
}

base_url = "https://raw.githubusercontent.com/google/fonts/main/ofl"

def to_snake_case(name):
    return name.lower()

for family, weights in fonts.items():
    for weight in weights:
        # BeVietnamPro doesn't have a static subfolder typically, but let's try static first, then root.
        # Actually, for most OFL fonts on google fonts:
        urls_to_try = [
            f"{base_url}/{family}/static/{family.capitalize() if family != 'bevietnampro' else 'BeVietnamPro'}-{weight}.ttf",
            f"{base_url}/{family}/{family.capitalize() if family != 'bevietnampro' else 'BeVietnamPro'}-{weight}.ttf",
            # Fallback for Inter
            f"{base_url}/{family}/static/Inter-{weight}.ttf" if family == 'inter' else "",
            # Fallback for PlusJakartaSans
            f"{base_url}/{family}/static/PlusJakartaSans-{weight}.ttf" if family == 'plusjakartasans' else "",
            # Fallback for HankenGrotesk
            f"{base_url}/{family}/static/HankenGrotesk-{weight}.ttf" if family == 'hankengrotesk' else "",
        ]
        
        success = False
        for url in urls_to_try:
            if not url: continue
            try:
                # lowercase filename for android resource requirements
                filename = f"{family}_{weight.lower()}.ttf".replace("-", "_")
                filepath = os.path.join(font_dir, filename)
                
                print(f"Trying {url}...")
                req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
                with urllib.request.urlopen(req) as response, open(filepath, 'wb') as out_file:
                    out_file.write(response.read())
                print(f"Downloaded {filename}")
                success = True
                break
            except Exception as e:
                pass
        
        if not success:
            print(f"Failed to download {family} {weight}")
