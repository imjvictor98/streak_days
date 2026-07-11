import os
import glob

replacements = {
    "import com.cvj.app.streakdays.domain.model.": "import com.cvj.app.streakdays.core.domain.model.",
    "import com.cvj.app.streakdays.domain.usecase.": "import com.cvj.app.streakdays.core.domain.usecase.",
    "import com.cvj.app.streakdays.domain.GoalRepository": "import com.cvj.app.streakdays.core.domain.repository.GoalRepository",
    "import com.cvj.app.streakdays.ui.create.": "import com.cvj.app.streakdays.feature.create.ui.",
    "import com.cvj.app.streakdays.ui.dashboard.": "import com.cvj.app.streakdays.feature.dashboard.ui.",
    "import com.cvj.app.streakdays.ui.details.": "import com.cvj.app.streakdays.feature.details.ui.",
    "import com.cvj.app.streakdays.ui.navigation.": "import com.cvj.app.streakdays.core.navigation.",
    "import com.cvj.app.streakdays.ui.theme.": "import com.cvj.app.streakdays.core.designsystem.",
    "import com.cvj.app.streakdays.ui.widget.": "import com.cvj.app.streakdays.core.widget.",
    "import com.cvj.app.streakdays.data.local.": "import com.cvj.app.streakdays.core.data.local.",
    "import com.cvj.app.streakdays.data.repository.": "import com.cvj.app.streakdays.core.data.repository."
}

for root, dirs, files in os.walk("app/src/main/java"):
    for file in files:
        if file.endswith(".kt"):
            path = os.path.join(root, file)
            with open(path, "r") as f:
                content = f.read()
            original = content
            for k, v in replacements.items():
                content = content.replace(k, v)
            
            # also replace package names in data
            if path.startswith("app/src/main/java/com/cvj/app/streakdays/core/data"):
                content = content.replace("package com.cvj.app.streakdays.data", "package com.cvj.app.streakdays.core.data")
                
            if content != original:
                with open(path, "w") as f:
                    f.write(content)
